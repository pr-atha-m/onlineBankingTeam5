import React from 'react'

class Alert extends React.Component{
    render(){
        const {message , onClose} = this.props;

        return (
            <div className="alert">
                <div className="alert-content">
                    <p>
                        {message}
                    </p>

                    <button onClick={onClose}>OK</button>
                </div>

    
            </div>
    
        )

    }


   
}
export default Alert
