import React, { useState } from 'react';

export const Example = () => {
    const [transformedUrl, setTransformedUrl] = useState('http://exampleOutput.com');
    const [inputVal, setInputVal] = useState('http://')
    const backendCall = (inputUrl) => {
        let responseStatus
        const backendUrl = "http://localhost:8080/urls/"
        fetch(backendUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ "actual": inputUrl })
        })
            .then(response => {
                responseStatus = response.status
                return response.json()
            })
            .then(body => {
                if (responseStatus === 200) {
                    setTransformedUrl(`${backendUrl}${body.hash}`)
                } else {
                    console.log('Something went wrong')
                }
            }
            )
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
            <a style={{ color: 'white' }} href={transformedUrl} target="_blank" rel="noopener noreferrer">{transformedUrl}</a>
        </div>
    );
}