import React from 'react'
import Navigation from "./Navigation";
import MainContent from "./MainContent";

const Canvas = () => {
    return (
        <div style={style}>
            <Navigation/>
            <MainContent/>
        </div>
    )
}

const style = {
    backgroundColor: ' #fff',
    width: '50%',
    height: '80vh'
}

export default Canvas
