import {Route, Switch} from "react-router-dom";
import Registration from "./Registration";
import Login from "./Login";
import React from "react";

const MainContent = () => {
    return (
        <div className='container'>
            <Switch>
                <Route exact path="/signup" component={Registration}/>
                {/*If you need to pass props use render*/}
                <Route exact path="/signin" render={() => <Login/>}/>
            </Switch>
        </div>
    );

}

export default MainContent;