from diagrams import Diagram
from diagrams.onprem.client import Users
from diagrams.onprem.container import Docker
from diagrams.programming.framework import Spring

graph_attr = {
  "fontsize": "20",
  "bgcolor": "white"  # transparent
}

with Diagram("", direction="LR", graph_attr=graph_attr, outformat="png", filename="testing_architecture"):
  users = Users("Integration Tests")
  backend = Spring("Application")
  database = Docker("PostgreSQL database")

  backend >> database
  users >> backend
