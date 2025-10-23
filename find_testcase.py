import pandas as pd

# Load the file
df = pd.read_csv('predicted_weights.csv')

# Clean up quotes if any
df['from'] = df['from'].astype(str).str.strip().str.replace('"', '')
df['to'] = df['to'].astype(str).str.strip().str.replace('"', '')

#  Get user input
source = input("Enter source (e.g., 40.72,-74.00): ").strip()
destination = input("Enter destination (e.g., 40.76,-73.96): ").strip()

# Check direct connection
direct = df[(df['from'] == source) & (df['to'] == destination)]
print("\n Direct path exists:" if not direct.empty else "\n❌ No direct path found.")

# Neighbors of source
intermediate = df[df['from'] == source]
print(f"\n Nodes directly reachable from {source}:")
print(intermediate['to'].unique())

#  Connections into destination
incoming = df[df['to'] == destination]
print(f"\n Nodes that connect to {destination}:")
print(incoming['from'].unique())
