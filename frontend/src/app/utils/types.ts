export type User = {
  userId: string;
  username: string;
  isAdmin: boolean;
};

export type Field = {
  [key: string]: string | number;
};

export type Specification = {
  id: string;
  userId: string;
  specs: Field[];
};

export type FlatFile = {
  id: string;
  fileName: string;
  userId: string;
  metaDataId: string;
};

export type RegisterResponse = {};

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
