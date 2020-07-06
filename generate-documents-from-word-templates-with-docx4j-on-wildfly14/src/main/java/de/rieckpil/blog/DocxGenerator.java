package de.rieckpil.blog;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import javax.ejb.Stateless;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

@Stateless
public class DocxGenerator {

  private static final String TEMPLATE_NAME = "template.docx";

  public byte[] generateDocxFileFromTemplate(UserInformation userInformation) throws Exception {

    InputStream templateInputStream = this.getClass().getClassLoader().getResourceAsStream(TEMPLATE_NAME);

    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

    MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

    VariablePrepare.prepare(wordMLPackage);

    HashMap<String, String> variables = new HashMap<>();
    variables.put("firstName", userInformation.getFirstName());
    variables.put("lastName", userInformation.getLastName());
    variables.put("salutation", userInformation.getSalutation());
    variables.put("message", userInformation.getMessage());

    documentPart.variableReplace(variables);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    wordMLPackage.save(outputStream);

    return outputStream.toByteArray();
  }

}
