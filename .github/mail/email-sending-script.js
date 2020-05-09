#! /usr/bin/env node

const sgMail = require('@sendgrid/mail');
sgMail.setApiKey(process.env.SENDGRID_API_KEY);

const msg = {
  to: 'driptaroop.das@gmail.com',
  from: 'driptaroop.das@gmail.com',
  subject: 'The build was successful',
  text: 'The build was successful and was deployed in heroku',
  html: '<p>The build was successful and was deployed in heroku</p>',
};

sgMail
.send(msg)
.then(() => console.log('Mail sent successfully'))
.catch(error => console.error(error.toString()));
