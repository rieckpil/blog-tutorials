# Codebase for the blog post [#HOWTO: Generate PDFs (Apache PDFBox) including Charts (XChart) with Java EE](https://rieckpil.de/howto-generate-pdfs-apache-pdfbox-including-charts-xchart-with-java-ee/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `charts-in-pdf-java-ee`
3. Build this project with `mvn clean package`
4. Start you docker engine
5. Build the docker image with `docker build -t charts .`
6. Run the docker container with `docker run -p 9080:9080 charts`
7. Wait until Open Liberty successfully started and then visit `http://localhost:9080/resources/reports` in your browser