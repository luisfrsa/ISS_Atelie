import React from 'react';
import Item from "./Item";
// import './App.css';

export default class Alert extends React.Component {

    constructor(props) {
        super(props);
    }

    renderAlert(notificacoes, nivel) {
        return notificacoes
            .filter(notificacao => notificacao.prioridade === nivel)
            .map(notificacao => <p><strong>{notificacao.nome}</strong>: {notificacao.descricao}</p>);
    }

    hasLevel(notificacoes, nivel) {
        return notificacoes.some(notificacao => notificacao.prioridade === nivel)
    }


    render() {
        return (
            <div>
                <div
                    className={[this.hasLevel(this.props.notificacoes, 0) ? "" : 'hidden', "alert alert-info"].join(" ")}
                    role="alert">
                    {this.renderAlert(this.props.notificacoes, 0)}
                </div>
                <div
                    className={[this.hasLevel(this.props.notificacoes, 1) ? "" : 'hidden', "alert alert-warning"].join(" ")}
                    role="alert">
                    {this.renderAlert(this.props.notificacoes, 1)}
                </div>
                <div
                    className={[this.hasLevel(this.props.notificacoes, 2) ? "" : 'hidden', "alert alert-danger"].join(" ")}
                    role="alert">
                    {this.renderAlert(this.props.notificacoes, 2)}
                </div>
            </div>
        );
    }

}




