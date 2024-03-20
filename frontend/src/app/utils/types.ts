export type User = {
  userId: string;
  username: string;
  isAdmin: boolean;
};

export type Specification = {
  id: string;
  userid: string;
};

export type RegsiterResponse = {};

export type RegisterRequestParams = {
  username: string;
  password: string;
  confirmPassword: string;
};

export type ErrorResponse = {
  status: number;
  title: string;
  details: [string];
};
