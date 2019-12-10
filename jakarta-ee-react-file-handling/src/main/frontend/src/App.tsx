import React, {useState} from 'react';
import {Container, Divider, Header, Image, Message} from 'semantic-ui-react';
import {ApiClient} from './ApiClient';
import FileUploadComponent from './FileUploadComponent';
import FileDownloadComponent from './FileDownloadComponent';

export interface StatusInformation {
  message: string
  color: 'green' | 'red'
}

const downloadFileInBrowser = (response: Response) => {
  const filename = response.headers.get('Content-Disposition')!.split('filename=')![1];
  response.blob().then(blob => {
    let url = window.URL.createObjectURL(blob);
    let a = document.createElement('a');
    a.href = url;
    a.download = filename;
    a.click();
  });
};

const App: React.FC = () => {

  const [statusInformation, setStatusInformation] = useState<StatusInformation>();

  return <Container text style={{marginTop: 10}}>
    <Image src='/jakartaEELogo.png' size='small' centered/>
    <Header as='h2' textAlign='center'>Jakarta EE & React File Handling</Header>
    {statusInformation && <Message color={statusInformation.color}>{statusInformation.message}</Message>}
    <FileUploadComponent uploadFile={file => {
      setStatusInformation(undefined);
      new ApiClient()
        .uploadFile(file)
        .then(response => setStatusInformation(
          {
            message: 'Successfully uploaded the file',
            color: 'green'
          }))
        .catch(error => setStatusInformation({
          message: 'Error occurred while uploading file',
          color: 'red'
        }))
    }}/>
    <Divider/>
    <FileDownloadComponent
      downloadFile={() => {
        setStatusInformation(undefined);
        new ApiClient()
          .downloadRandomFile()
          .then(response => {
            setStatusInformation({
              message: 'Successfully downloaded a random file',
              color: 'green'
            });
            downloadFileInBrowser(response);
          })
          .catch(error => setStatusInformation({
            message: 'Error occurred while downloading file',
            color: 'red'
          }))
      }}/>
  </Container>;
};

export default App;
