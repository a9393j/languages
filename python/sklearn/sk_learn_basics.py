# import sklearn

# print("Hello")
# a = 5
# b = 6
# print(a+b)

# import numpy as np
# from sklearn.cluster import HDBSCAN
# from sklearn.datasets import load_digits
# from sklearn.metrics import v_measure_score

# X, true_labels = load_digits(return_X_y=True)
# print(f"number of digits: {len(np.unique(true_labels))}")

# hdbscan = HDBSCAN(min_cluster_size=15).fit(X)
# non_noisy_labels = hdbscan.labels_[hdbscan.labels_ != -1]
# print(f"number of clusters found: {len(np.unique(non_noisy_labels))}")

# print(v_measure_score(true_labels[hdbscan.labels_ != -1], non_noisy_labels))


import numpy as np
from sklearn.model_selection import cross_val_score
from sklearn.datasets import make_low_rank_matrix
from sklearn.ensemble import HistGradientBoostingRegressor

n_samples, n_features = 500, 10
rng = np.random.RandomState(0)
X = make_low_rank_matrix(n_samples, n_features, random_state=rng)
coef = rng.uniform(low=-10, high=20, size=n_features)
y = rng.gamma(shape=2, scale=np.exp(X @ coef) / 2)
gbdt = HistGradientBoostingRegressor(loss="gamma")
cross_val_score(gbdt, X, y).mean()

print(gbdt)