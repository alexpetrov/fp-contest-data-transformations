#!/bin/bash
lein uberjar && time java -jar target/uberjar/fp-contest-data-transformations-0.1.0-SNAPSHOT-standalone.jar
