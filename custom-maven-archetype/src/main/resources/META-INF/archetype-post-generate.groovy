def file = new File(request.getOutputDirectory(), request.getArtifactId() + "/myScript.sh")
file.setExecutable(true, false)
