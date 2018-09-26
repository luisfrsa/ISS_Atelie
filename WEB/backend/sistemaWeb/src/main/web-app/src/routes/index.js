import React from 'react';
import {
    BrowserRouter,
    Route,
    Switch,
} from 'react-router-dom'

// import Auth from './Auth';
import Login from './Login';
import Home from './Home';
// import Register from './Register';

export default () => (
    <BrowserRouter>
        <Switch>
            <Route exact path="/login" render={props => <Login {...props} />} />
            <Route exact path="/home" render={props => <Home {...props} />} />
            {/*<Route exact path="/auth" render={props => <Auth {...props} />} />*/}
        </Switch>
    </BrowserRouter>
);
