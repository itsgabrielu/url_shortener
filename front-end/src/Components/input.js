import React, { useState } from 'react';

export const Example = () => {
    const [transformedUrl, setTransformedUrl] = useState('http://exampleOutput.com');
    const [errorDisplay, setErrorDisplay] = useState(false)
    const [errorText, setErrorText] = useState('')
    const [inputVal, setInputVal] = useState('http://')
    const backendCall = (inputUrl) => {
        const backendUrl = "http://localhost:8080/urls/"
        fetch(backendUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ "actual": inputUrl })
        })
            .then(response => {
                if (response.ok) {
                    setErrorDisplay(false)
                    return response.json()
                } else {
                    throw response.status
                }
            })
            .then(body => {
                setTransformedUrl(`${backendUrl}${body.hash}`)
            })
            .catch(e => {
                setErrorDisplay(true)
                switch (e) {
                    case 417:
                        setErrorText('Invalid url')
                        break;
                    case 500:
                        setErrorText('Server error')
                        break;
                    default:
                        setErrorText('Please try again later')
                }
            })
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        backendCall(inputVal)
    }

    const collectInput = (e) => {
        setInputVal(e.target.value)
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input type="text" value={inputVal} onChange={collectInput} />
                <button >
                    Submit
                </button>
            </form>
            {errorDisplay ?
                <p> Oops: {errorText}</p> :
                <a style={{ color: 'white' }} href={transformedUrl} target="_blank" rel="noopener noreferrer">{transformedUrl}</a>}
        </div>
    );
}