import React, {Component} from 'react';
import {Button, Input, Checkbox} from 'antd';
import axios from "axios";
import Tabela from "./Tabela";
// import './App.css';

export default class Home extends React.Component {

    componentDidMount(){
        this.getData();
    }

    constructor(props){
        super(props);
        this.state = {vendas:[]};
    }

    getData = () => {
        axios.get('http://localhost:8080/sacola/')
            .then(request => {
                //Ve o que retorna
                this.setState({ vendas: request.data });
            })
            .catch(e=>{
                try {
                    alert(e.response.data.message);
                } catch (err) {
                    alert("Erro interno");
                }
            })
    }

    render() {
        console.log('Rendering ', this.state.vendas);
        return (
            <div className="Home container mt-5">
                <div className="row justify-content-md-center">
                    <div className="col col-md-5">
                        <h1 className="text-center">Home</h1>
                        <Tabela vendas={this.state.vendas}/>
                    </div>
                </div>
            </div>
        );
    }
}

