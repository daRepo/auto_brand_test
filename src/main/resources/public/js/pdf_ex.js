function submit() {
    const input = document.getElementById('0');
    const file = input.files[0];
    if (!file) {
        alert('Please select a file before submitting.');
        return;
    }

    const formData = new FormData();
    formData.append('file', file);

    fetch('/scraper/data', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        return response.blob();
    })
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = "extracted-data.csv";
        document.body.appendChild(a);
        a.click();
        a.remove();
        window.URL.revokeObjectURL(url);
        alert('File submitted and CSV downloaded successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error submitting the file or downloading CSV.');
    });
}
