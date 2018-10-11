import React, {Component} from 'react';
import {Button, Input, Checkbox} from 'antd';
import axios from "axios";
// import './App.css';

export default class Item extends React.Component {

    // renderItem() {
    //     return this.props.dados.map((item) => <Item dados={item} />);
    // }
    
    render() {
        return (
                <tr>
                <td>{this.props.dados.consultora.nome}</td>
                <td>{this.props.dados.dataCriacao}</td>
                <td>{this.props.dados.dataAcerto}</td>
                    <td>654654</td>
                </tr>
        );
    }
}



