// import React, {Component} from 'react';
// import {Button, Input, Checkbox} from 'antd';
// import './App.css';
//
// class App extends Component {
// <Routes />
//     state = {
//         username: '',
//         password: '',
//     }
//
//     onChange = (e) => {
//         console.log("changed");
//         console.log(e.target);
//         this.setState({
//             [e.target.name]: e.target.value,
//         });
//     }
//
//     onSubmit = () => {
//         this.props.history.push("/login")
//
//     }
//
//     render() {
//         return (
//             <div className="App container mt-5">
//                 <div className="row justify-content-md-center">
//                     <div className="col col-md-5">
//                         <h1 className="text-center">Login</h1>
//                         <Input
//                             name='username'
//                             placeholder='Username'
//                             onChange={e => this.onChange(e)}
//                             value={this.state.username}
//                             className="form-control  mt-3"/>
//                         <Input
//                             name='password'
//                             placeholder='Password'
//                             type='password'
//                             onChange={e => this.onChange(e)}
//                             value={this.state.password}
//                             className="form-control mt-3" />
//
//                         {/*<Redirect to="/login"*/}
//                               {/*className="btn btn-primary mt-3 float-right">login</Redirect>*/}
//                         <Button onClick={() => this.onSubmit()}
//                                 type="primary"
//                                 className="btn btn-primary mt-3 float-right"
//                         >Login</Button>
//
//                     </div>
//                 </div>
//             </div>
//         );
//     }
// }
//
// export default App;
