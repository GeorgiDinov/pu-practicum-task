import {Route, Switch} from "react-router-dom";
import SignUp from "./SignUp";
import SignIn from "./SignIn";
import React from "react";
import ItemPane from "./Item/ItemPane";

const MainContent = () => {
    return (
        <div className='container'>
            <Switch>
                <Route exact path="/signup" component={SignUp}/>
                {/*If you need to pass props use render*/}
                <Route exact path="/signin" component={SignIn}/>

                <Route exact path="/main" render={() => <ItemPane/>}/>
            </Switch>
        </div>
    );

}

export default MainContent;