const BASE_URL = 'http://localhost:8080';

export const register = async (
  username: string,
  password: string,
  confirmPassword: string
) => {
  const res = await fetch(`${BASE_URL}/users/register`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, password, confirmPassword }),
  });

  if (!res.ok) {
    throw new Error('Something went wrong');
  }
  return await res.json();
};

export const login = async (username: string, password: string) => {
  const res = await fetch(`${BASE_URL}/users/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      username,
      password,
    }),
  });
  if (res.ok) {
    const result = await res.json();
    console.log('result', result);
  } else {
    console.error('Login failed', res.statusText);
  }
};
