import {Button, Col, Form} from "react-bootstrap";
import React from "react";

const ItemForm = () => {



    return (
        <div className='container'>
            <Form>
                <Form.Row>
                    <Form.Group as={Col} className="col-6" controlId="item">
                        <Form.Control type="text" placeholder="Item"/>
                    </Form.Group>
                    <Form.Group as={Col} className="col-2" controlId="quantity">
                        <Form.Control type="number" min="0" placeholder="Quantity"/>
                    </Form.Group>
                    <Button variant="outline-info" type="submit">Add</Button>
                </Form.Row>
            </Form>
        </div>
    );
}

export default ItemForm;