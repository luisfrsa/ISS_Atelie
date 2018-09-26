import React, {Component} from 'react';
import {Button, Input, Checkbox} from 'antd';
// import './App.css';

export default class Home extends React.Component {
    state = {
        username: '',
        password: '',
    }

    onChange = (e) => {
        console.log("changed");
        console.log(e.target);
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    onSubmit = () => {
        this.props.history.push("/login")

    }

    render() {
        return (
            <div className="Home container mt-5">
                <div className="row justify-content-md-center">
                    <div className="col col-md-5">
                        <h1 className="text-center">Home</h1>
                    </div>
                </div>
            </div>
        );
    }
}

