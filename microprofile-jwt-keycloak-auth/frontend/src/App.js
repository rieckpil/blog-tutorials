import React from 'react';
import { Button, Container, Header, Icon, Message, Grid } from 'semantic-ui-react';
import Keycloak from 'keycloak-js';
import axios from 'axios';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = { keycloak: null, authenticated: false, backendData: null };
  }

  componentDidMount = () => {
    const keycloak = Keycloak("/keycloak.json");

    keycloak.init({ onLoad: 'login-required' }).success(authenticated => {
      this.setState({ keycloak: keycloak, authenticated: authenticated });
    }).error(err => {
      alert(err);
    });
  }

  decodeJWT = (token) => {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.stringify(JSON.parse(window.atob(base64)), null, 4);
  }

  fetchBackendData = () => {
    axios.get('http://localhost:8080/resources/secure', { headers: { 'Authorization': ' Bearer ' + this.state.keycloak.token } })
      .then(res => this.setState({ backendData: res.data }));
  }

  render() {

    return (
      <Container>
        <br />
        <Header as='h2' icon textAlign='center'>
          <Icon name='user secret' />
          MicroProfile JWT with Keycloak
         <Header.Subheader>Simple protoype with React, Keycloak and MicroProfile/Jakarta EE</Header.Subheader>
        </Header>
        <Grid centered columns={2}>
          <Grid.Column>
            {this.state.authenticated ?
              <Message
                positive
                icon='check'
                header='You are currently logged in'
                content='Try to access the REST API and get the secured message.'
              /> :
              <Message
                negative
                icon='times'
                header='You are currently not logged in'
                content='Wait until you get redirected to Keycloak.'
              />}
          </Grid.Column>
        </Grid>

        <Grid centered columns={2}>
          <Grid.Column textAlign='center'>
            <Header as='h2'>
              Access MicroProfile REST API
            </Header>
            <Button onClick={this.fetchBackendData} disabled={!this.state.authenticated}>Access REST API</Button>
            <Message>
             <pre>{this.state.backendData}</pre>
            </Message>
          </Grid.Column>
        </Grid>

        <Grid centered columns={2}>
          <Grid.Column>
            <Header as='h2' textAlign='center'>
              Your decoded JWT token
            </Header>
            <Container text>
              <pre>
                {this.state.keycloak ? this.decodeJWT(this.state.keycloak.token) : 'n.A.'}
              </pre>
            </Container >
          </Grid.Column>
        </Grid>

      </Container>
    );
  }
}

export default App;
