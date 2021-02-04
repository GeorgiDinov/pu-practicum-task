import React from 'react'
import Navigation from "./Navigation";
import {Route, Switch} from 'react-router-dom'
import Registration from "./Registration";
import Login from "./Login";

const Canvas = () => {
    return (
        <div style={style}>
            <Navigation/>
            <Switch>
                <Route exact path="/register" component={Registration}/>
                {/*If you need to pass props use render*/}
                <Route exact path="/login" render={() => <Login/>}/>
            </Switch>
        </div>
    )
}

const style = {
    backgroundColor: ' #fff',
    width: '50%',
    height: '80vh'
}

export default Canvas
