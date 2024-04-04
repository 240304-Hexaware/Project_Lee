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
  name: string;
};

export type FlatFile = {
  id: string;
  fileName: string;
  userId: string;
  metaDataId: string;
};

export type LoginRequestParams = {
  username: string;
  password: string;
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

export type ParsingRequestParams = {
  specId: string;
  rawFileId: string;
};

export type ParsedDataContainer = {
  id: string;
  userId: string;
  metadataId: string;
  parsedData: ParsedData[];
};

export type ParsedData = {
  [key: string]: string | number;
};

export type Metadata = {
  id: string;
  flatFile: FlatFile;
  parsedData: ParsedDataContainer;
  specFile: Specification;
  parsedAt: string;
};
