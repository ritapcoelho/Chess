import React from 'react';
import './App.css';

let URL = 'ws://localhost:8003/chess/'

class Connection extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      messages: []
    }
  }

  ws = new WebSocket(URL + this.props.name);
  
  componentDidMount() {
    this.ws.onopen = () => {
      // on connecting, do nothing but log it to the console
      console.log('connected')
    }
    this.ws.onmessage = evt => {
      // on receiving a message, add it to the list of messages
      const message = JSON.parse(evt.data)
      console.log(message);
      this.setState({messages: [...this.state.messages, evt.data]})
    }
  }

  startGameMessage = messageString => {
    // on submitting the ChatInput form, send the message, add it to the list and reset the input
    const message = { messageType: 'CREATE_GAME', color: 'WHITE' }
    this.ws.send(JSON.stringify(message))
  }

  render() {
    return (
      <div>
        <button onClick={this.startGameMessage}>Start Game</button>
        {this.state.messages.map(txt => <p>{txt}</p>)}
      </div>
    )
  }
}

export default Connection;
