import React, {Component} from 'react';
import {Button, Input, Checkbox} from 'antd';
import axios from 'axios'

// import './App.css';

export default class Login extends React.Component {
    state = {
        cpf: '',
        password: '',
        error:''
    }

    onChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    onSubmit = () => {
        console.log('asd');
        axios.get('http://localhost:8080/consultora/findByCpf/'+this.state.cpf+'/'+this.state.password)
            .then(response => {
                this.state.error = '';
                localStorage.setItem('authenticated',true);
                this.props.history.push("/");
            })
            .catch(e=>{
                try {
                    this.setState({error: e.response.data.message});
                } catch (err) {
                    this.setState({error: "Erro interno"});
                }
            })

    }

    render() {
        return (
            <div className="Login container mt-5">
                <div className="row justify-content-md-center">
                    <div className="col col-md-5">
                        <h1 className="text-center">Login de Consultora</h1>
                        <Input
                            name='cpf'
                            placeholder='CPF'
                            onChange={e => this.onChange(e)}
                            value={this.state.cpf}
                            className="form-control  mt-3"/>
                        <Input
                            name='password'
                            placeholder='Senha'
                            type='password'
                            onChange={e => this.onChange(e)}
                            value={this.state.password}
                            className="form-control mt-3" />
                        {this.state.error.length > 0 &&
                        <div className="alert alert-danger">
                            <strong>{this.state.error}</strong>
                        </div>
                        }
                        <Button onClick={() => this.onSubmit()}
                                type="primary"
                                className="btn btn-primary mt-3 float-right"
                        >Login</Button>

                    </div>
                </div>
            </div>
        );
    }
}

