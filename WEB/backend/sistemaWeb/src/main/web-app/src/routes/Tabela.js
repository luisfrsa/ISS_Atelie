import React, {Component} from 'react';
import {Button, Input, Checkbox} from 'antd';
import axios from "axios";
import Item from "./Item";
// import './App.css';

export default class Tabela extends React.Component {

    constructor(props) {
        super(props);
    }

    rendeListItem(){
        return this.props.vendas.map((item) => <Item dados={item} />);
    }

    render() {
        return (
            <table className="table table-responsive table-striped">
                <thead>
                <tr>
                    <th>Consultora</th>
                    <th>Data Criação</th>
                    <th>Data Acerto</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                    {this.rendeListItem()}                   
                </tbody>
            </table>
        );
    }
}



