import React from 'react'
import healthyLogo from './healthy_logo.png'
import { useQuery } from "react-query"
import { ReactQueryDevtools } from 'react-query-devtools'
import HealthTable from './components/HealthTable'
import CssBaseline from '@material-ui/core/CssBaseline'
import Container from '@material-ui/core/Container'
import './App.css'

const containerBasic = {
  padding: "2%",
  marginTop: "40px",
  marginBottom: "40px",
  justifyContent: 'center',
  alignItems: 'center',
  display: "flex",
}

const healthy_api = window._env_.REACT_APP_HEALTHY_API_URL
  ? window._env_.REACT_APP_HEALTHY_API_URL 
  : "http://localhost:8080"

function App() {
  const { isLoading, error, data } = useQuery("services", () =>
    fetch(
      // "http://localhost:8080/health"
      healthy_api+"/health"
    ).then((res) => res.json())
  ,{
    refetchInterval: 60*1000,
    refetchIntervalInBackground: true
  });
  if (isLoading) return "Loading...";

  if (error) return "An error has occurred: " + error.message;

  return (
    <div>
      <React.Fragment>
        <CssBaseline />
        <Container maxWidth="lg">
          <Container style={containerBasic}>
            <img src={healthyLogo} alt="Logo" />
          </Container>
          <HealthTable list={data}></HealthTable>
        </Container>
      </React.Fragment>
      <ReactQueryDevtools initialIsOpen />
    </div >
  );
}

export default App;
