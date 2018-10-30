import React, {Component} from 'react';
import {Button, Input, Checkbox} from 'antd';
import axios from "axios";
import Tabela from "./Tabela";
import Alert from "./Alert";
// import './App.css';

export default class Home extends React.Component {

    componentDidMount() {
        this.getData();
    }

    constructor(props) {
        super(props);
        this.state = {vendas: [],notificacoes:[]};
    }

    getData = () => {
        axios.get('http://localhost:8080/sacola/')
            .then(request => {
                this.setState({vendas: request.data});
            })
            .catch(e => {
                try {
                    alert(e.response.data.message);
                } catch (err) {
                    alert("Erro interno");
                }
            });
        axios.get('http://localhost:8080/notificacoes/')
            .then(request => {
                this.setState({notificacoes: request.data});
            })
            .catch(e => {
                try {
                    alert(e.response.data.message);
                } catch (err) {
                    alert("Erro interno");
                }
            })
    }

    render() {
        // console.log('Rendering ', this.state.vendas);
        return (
            <div className="Home container md-8">
                <div className="row justify-content-md-center">
                    <div className="col col-md-8">
                        <h1 className="text-center titulo">Relatório de vendas</h1>
                        <Alert notificacoes={this.state.notificacoes}/>
                        <Tabela  vendas={this.state.vendas}/>
                    </div>
                </div>
            </div>
        );
    }
}

