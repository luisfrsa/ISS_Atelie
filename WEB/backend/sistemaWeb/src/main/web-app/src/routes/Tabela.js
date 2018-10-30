import React, {Component} from 'react';
import {Button, Input, Checkbox} from 'antd';
import axios from "axios";
import Item from "./Item";
// import './App.css';

export default class Tabela extends React.Component {

    constructor(props) {
        super(props);
    }

    rendeListItem() {
        return this.props.vendas.map((item) => {
            const key=item.id+"_"+Math.floor(Math.random() * 100) + 1;
            return <Item dados={item} key={key}/>
        });
    }

    renderTotal() {
        return this.props.vendas.reduce((sum, sacola) =>
            sum + sacola.listaItens.reduce((su, item) =>
            su + (item.quantidade * item.produto.valor)
            , 0)
            , 0);
    }


    render() {
        return (
            <table className="centering table table-responsive table-striped">
                <thead>
                <tr>
                    <th>Status</th>
                    <th>Consultora</th>
                    <th>Data Criação</th>
                    <th>Data Acerto</th>
                    <th>Produtos</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                {this.rendeListItem()}
                <tr>
                    <td>Total</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>R$ {this.renderTotal()}</td>
                </tr>
                </tbody>
            </table>
        );
    }
}



