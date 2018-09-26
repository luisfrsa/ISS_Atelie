import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
// import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import Routes from './routes';
// import registerServiceWorker from './registerServiceWorker';

// ReactDOM.render(<App/>, document.getElementById('root'));
// registerServiceWorker();


const App = () => (
    <Routes/>
);

ReactDOM.render(<App />, document.getElementById('root'));