export const ARG_BASES_TOTAL = process.argv.includes('--bases') ? parseInt(process.argv[process.argv.indexOf('--bases') + 1] ?? '4') : 4;
export const ARG_UNITS_START = process.argv.includes('--units') ? parseInt(process.argv[process.argv.indexOf('--units') + 1] ?? '2') : 2;
export const ARG_ROUNDS_TOTAL = process.argv.includes('--rounds') ? parseInt(process.argv[process.argv.indexOf('--rounds') + 1] ?? '5') : 5;
export const ARG_NO_WAIT = process.argv.includes('--no-wait');