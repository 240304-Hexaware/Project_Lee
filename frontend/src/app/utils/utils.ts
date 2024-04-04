export const download = (file: string, filename: string) => {
  const downloadFile = new Blob([file], {
    type: 'text/plain',
  });
  const linkElement = document.createElement('a');
  linkElement.href = URL.createObjectURL(downloadFile);
  linkElement.download = filename;
  linkElement.click();
  linkElement.remove();
};
