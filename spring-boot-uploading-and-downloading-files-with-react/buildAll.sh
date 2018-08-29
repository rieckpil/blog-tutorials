#!/bin/bash
cd frontend && npm install && npm run build && cd ..
cd backend && mvn clean package
