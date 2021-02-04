import Canvas from "./Components/Canvas";


function App() {

    return (
        <div className="container-fluid" style={style}>
            <Canvas/>
        </div>
    );
}

const style = {
    backgroundImage: 'url(https://images.pexels.com/photos/2449665/pexels-photo-2449665.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260)',
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    minHeight: '100vh',
    display: 'flex',
    justifyContent:'center',
    alignItems: 'center'
}

export default App;
