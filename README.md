# Lesson_CollectionProject

"Product Star" course homework (external, not ITMO).

A console app for managing a student collection: a command handler
(`StudentCommandHandler`) dispatches typed `Command`s over a `StudentStorage`,
with a separate `StudentSurnameStorage` index, input validation (`Validator`),
and custom exceptions for out-of-range access and bad number formats.
