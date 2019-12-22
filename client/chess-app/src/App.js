import React, { Component } from 'react';
import './App.css';
import Connection from './Connection';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      value: '',
      namePicked: false
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    this.setState({namePicked: true})
  }

  render() {
    let bod;

    if (!this.state.namePicked) {
      bod = <form onSubmit={this.handleSubmit}>
        <label>
          Name:
          <input type="text" value={this.state.value} onChange={this.handleChange} />
        </label>
        <input type="submit" value="Submit" />
      </form>
    } else {
      bod = <Connection name={this.state.value}></Connection>
    }

  return <div>{bod}</div>
  }
}

export default App;
