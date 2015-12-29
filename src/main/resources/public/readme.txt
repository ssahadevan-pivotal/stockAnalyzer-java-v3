



From Eclipse:
mvn package


To Push to Pivotal Cloud Foundry:
From a terminal:


cd /Users/ssahadevan/Documents/workspace/StockAnalyzer-java/target

cf push stockAnalyzer -p ./stockAnalyzer-0.1.0.jar