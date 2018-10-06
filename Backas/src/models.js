const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const TreeSchema = new Schema(
  {
          email: {
                  type: String,
                  lowercase: true,
                  trim: true,
                  index: true,
                  unique: true,
                  required: true,
          },
          username: {
                  type: String,
                  lowercase: true,
                  trim: true,
                  index: true,
                  unique: true,
                  required: true,
          },
          password: {
                  type: String,
                  required: true,
                  bcrypt: true,
          },
          name: {
                  type: String,
                  trim: true,
                  required: true,
          },
          bio: {
                  type: String,
                  trim: true,
                  default: '',
          },
          url: {
                  type: String,
                  trim: true,
                  default: '',
          },
          twitter: {
                  type: String,
                  trim: true,
                  default: '',
          },
          background: {
                  type: Number,
                  default: 1,
          },
          interests: {
                  type: Schema.Types.Mixed,
                  default: [],
          },
          preferences: {
                  notifications: {
                          daily: {
                                  type: Boolean,
                                  default: false,
                          },
                          weekly: {
                                  type: Boolean,
                                  default: true,
                          },
                          follows: {
                                  type: Boolean,
                                  default: true,
                          },
                  },
          },
          recoveryCode: {
                  type: String,
                  trim: true,
                  default: '',
          },
          active: {
                  type: Boolean,
                  default: true,
          },
          admin: {
                  type: Boolean,
                  default: false,
          },
  },
  { collection: 'trees' },
);

module.exports = {Tree: mongosose.model('Tree', TreeSchema)};