#!/bin/bash

for i in {1..10}; do
  echo -e "Access API with user 'rieckpil' API $i times"
  curl -H 'Authorization: Basic cmllY2twaWw6SGVsbG9Xb3JsZA==' http://localhost:8080/api-rate-limiting/resources/stocks
  echo -e "\n"
done
