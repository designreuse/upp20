export class FormFieldBase<T> {
  id: string;
  label: string;
  defaultValue: any;
  type: any;
  typeName: string;
  validationConstraints: Constraint[];
  properties: FormFieldProperties;
  // value: any,

  value: T;
  key: string;
  required: boolean;
  order: number;
  controlType: string;
  options: { key: string, value: string }[];

  constructor(options: {
    id?: string,
    label?: string;
    defaultValue?: any;
    type?: any;
    typeName?: string;
    validationConstraints?: Constraint[];
    properties?: any;

    value?: T;
    key?: string;
    required?: boolean;
    order?: number;
    controlType?: string;
    options?: { key: string, value: string }[];
  } = {}) {
    this.id = options.id || '';
    this.label = options.label || '';
    this.defaultValue = options.defaultValue;
    this.type = options.type;
    this.typeName = options.typeName || '';
    this.validationConstraints = options.validationConstraints || [];
    this.properties = options.properties;

    this.value = options.value as any;
    this.key = options.key || '';
    this.required = !!options.required;
    this.order = options.order === undefined ? 1 : options.order;
    this.controlType = options.controlType || '';
    this.options = options.options || [];
  }
}

export interface Constraint {
  name: string;
  configuration?: any;
}

export interface FormFieldProperties {
  type: string;   // inputType
  controlType: string;
}
