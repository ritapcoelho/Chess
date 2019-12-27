import React, { Component } from 'react';
import './App.css';
import Connection from './Connection';
import Chess from 'react-chess';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      value: '',
      namePicked: false,
      pieces: Chess.getDefaultLineup()
    }
    this.handleMovePiece = this.handleMovePiece.bind(this)
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    this.setState({namePicked: true})
  }

  handleMovePiece(piece, fromSquare, toSquare) {
    const newPieces = this.state.pieces
        .map((curr, index) => {
          if (piece.index === index) {
            return `${piece.name}@${toSquare}`
          } else if (curr.indexOf(toSquare) === 2) {
            return false // To be removed from the board
          }
          return curr
        })
        .filter(Boolean)

    this.setState({pieces: newPieces})
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
      bod = <div>
        <Connection name={this.state.value}/>
        <Chess pieces={this.state.pieces} onMovePiece={this.handleMovePiece}/>
      </div>
    }

    return <div>{bod}</div>
  }
}

export default App;
