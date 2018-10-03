import React from 'react'
import {Redirect, Route} from 'react-router-dom'

const PrivateRoute = ({component: Component, ...rest}) => {

    // Add your own authentication on the below line.
    const isLoggedIn = JSON.parse(localStorage.getItem('authenticated'));
    return (
        <Route
            {...rest}
            render={props =>
                isLoggedIn ? (
                    <Component {...props} />
                ) : (
                    <Redirect to={{pathname: '/login', state: {from: props.location}}}/>
                )
            }
        />
    )
}

export default PrivateRoute