# nyc-taxi-shortest-path
ML + Graph project: linear regression for edge weight prediction and Dijkstra’s algorithm for shortest path in NYC taxi data.

This project combines Machine Learning (Python) and Graph Algorithms (Java) to analyze NYC taxi trip data.
We predict edge weights (travel times) using Linear Regression and then use Dijkstra’s algorithm to compute the shortest path between pickup and drop-off locations.



## 📊 Dataset

- Based on the [NYC Taxi Trip Duration dataset] available on Kaggle.
- Due to size limits, the **full CSV is not included** in this repo.
- A smaller sample file (`predicted_weights_sample.csv`) and a sample initial dataset are provided for quick understanding.
- To run on the full dataset, download it separately from Kaggle.

## 🔧 Methods

- **Machine Learning**: Linear Regression used to predict edge weights (trip times).
- **Python (`find_testcase.py`)** (optional for our understanding):
  - Reads predicted weights from a CSV file.
  - Cleans and processes data.
  - Allows users to check connectivity between locations.
- **Java (`Graph.java` & `Main.java`)**:
  - Implements **Dijkstra’s algorithm** to find the shortest path.
  - Graph data is built from the predicted weights.
