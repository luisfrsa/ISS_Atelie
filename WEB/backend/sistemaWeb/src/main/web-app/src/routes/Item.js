import React, {Component} from 'react';
import {Button, Input, Checkbox} from 'antd';
import axios from "axios";
// import './App.css';

export default class Item extends React.Component {


    rendeListProdutos(lista) {
        return lista.map(item=>{
            return <p>{item.quantidade + " x " + item.produto.descricao + ", R$" + item.produto.valor}</p>
        });
    }

    renderTotal(lista) {
        return "R$ "+lista.reduce((sum, item) => {
            return sum + (item.quantidade * item.produto.valor);
        }, 0);
    }

    formataData(data){
        const spl = data.split("-");
        return spl[2]+"/"+spl[1]+"/"+spl[0];
    }

    isFinalizada(finalizada){
        return finalizada?"Finalizada":"Não finalizada";
    }

    render() {
        return (
            <tr>
                <td>{this.isFinalizada(this.props.dados.finalizada)}</td>
                <td>{this.props.dados.consultora.nome}</td>
                <td>{this.formataData(this.props.dados.dataCriacao)}</td>
                <td>{this.formataData(this.props.dados.dataAcerto)}</td>
                <td>{this.rendeListProdutos(this.props.dados.listaItens)}</td>
                <td>{this.renderTotal(this.props.dados.listaItens)}</td>
            </tr>
        );
    }
}



