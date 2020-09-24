import React from 'react';
import './App.css';
import Axios from 'axios';
import { HotTable } from '@handsontable/react';
import 'handsontable/dist/handsontable.full.css';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
   columns: [
   ],
        data: [
        ],
        newArrColumns: [],
        newArrData: [],
        newArrDataForPair: []
    };
  }

  componentDidMount() {
      //console.log(this.state.columns);
      Axios({
          //PLEASE FIX URL BASED ON YOUR LOCAL MACHINE PORT WHICH SET FOR BACKEND NODE
          method: 'POST',
          url: `http://localhost:8088/test-jersey-rest-maven-tomcat/rest/testservice/test12/`,
          data: null,
          headers: {
              'Content-Type' : 'application/json'
          }
      }).then(async (response) => {
          const json = response.data[0];
          const jsonBig = response.data;
          for (let key in json) {
              if (json.hasOwnProperty(key)) {
                  this.state.newArrColumns.push(key);
              }
          }

          for(let a=0; a<jsonBig.length; a++){
              const jsonPair = response.data[a];
              for(let key in jsonPair){
                  if (jsonPair.hasOwnProperty(key)) {
                      this.state.newArrData.push(jsonPair[key]);
                  }
              }
              this.state.data.push(this.state.newArrData);
              this.state.newArrData = [];
          }
          this.state.columns.push(this.state.newArrColumns);
          this.forceUpdate();
      }).catch((err) => {
          console.log(err);
      })
  }

  render() {
      return(
        <div className="App">
          <header className="App-header">
        <p style={ {color: "white"}}>CFM - Mulakat IOT Projesi -- IREM ERDIL</p>
            <div>
              <HotTable nestedHeaders={this.state.columns}  data={this.state.data}  autoRowSize={true}  autoWrapRow={true} dropdownMenu={true} filters={true} manualColumnResize={true} columnSorting={true} colHeaders={true} rowHeaders={true} width="1200" height="600" licenseKey="non-commercial-and-evaluation" />
            </div>
          </header>
        </div>
    )
  }
}
export default App;