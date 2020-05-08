

 aws s3api put-object --bucket image-uploads-java-thumbnail-example --key uploads/myPicture.png --body myPicture.png --profile serverless-admin
 aws s3api list-objects-v2 --bucket image-uploads-java-thumbnail-example --profile serverless-admin
