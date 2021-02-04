import React from 'react'
import Registration from "./Registration"

const Canvas = () => {
    return (
        <div className='container' style={style}>
            <div className='row'>
                <div className='col'>
                    <p>Canvas</p>
                </div>
            </div>

            <Registration/>
        </div>
    )
}

const style = {
    backgroundColor: ' #fff',
    width: '50%',
    height: '80vh'
}

export default Canvas
