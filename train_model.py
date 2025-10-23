import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression
import joblib

# Load dataset
df = pd.read_csv("nyc_taxi_trip_duration.csv")

# Create pickup and dropoff zones (rounded for generalization)
df['pickup_zone'] = df['pickup_latitude'].round(2).astype(str) + "," + df['pickup_longitude'].round(2).astype(str)
df['dropoff_zone'] = df['dropoff_latitude'].round(2).astype(str) + "," + df['dropoff_longitude'].round(2).astype(str)

# Extract pickup hour
df['hour'] = pd.to_datetime(df['pickup_datetime']).dt.hour

# Euclidean distance
def euclidean_distance(lat1, lon1, lat2, lon2):
    return np.sqrt((lat2 - lat1)**2 + (lon2 - lon1)**2)

df['distance'] = euclidean_distance(df['pickup_latitude'], df['pickup_longitude'],
                                    df['dropoff_latitude'], df['dropoff_longitude'])

# Filter noisy data
df = df[(df['distance'] > 0) & (df['trip_duration'] < 10000)]

# Train ML model
X = df[['hour', 'distance']]
y = df['trip_duration']
model = LinearRegression()
model.fit(X, y)

# Predict & export for graph use
df['predicted_duration'] = model.predict(X)
df_out = df[['pickup_zone', 'dropoff_zone', 'predicted_duration']]
df_out.columns = ['from', 'to', 'weight']
df_out.to_csv("predicted_weights.csv", index=False)
