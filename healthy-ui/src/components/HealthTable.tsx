import React from 'react';
import { withStyles, Theme, createStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import CheckIcon from '@material-ui/icons/Check';
import ErrorOutlineOutlinedIcon from '@material-ui/icons/ErrorOutlineOutlined';
import HelpOutlineOutlinedIcon from '@material-ui/icons/HelpOutlineOutlined';

const StyledTableCell = withStyles((theme: Theme) =>
  createStyles({
    head: {
      backgroundColor: "#00AE2E",
      color: theme.palette.common.white,
      fontSize: 19,
    },
    body: {
      fontSize: 16,
    },
  }),
)(TableCell);

const StyledTableRow = withStyles((theme: Theme) =>
  createStyles({
    root: {
      '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
      },
      fontSize: 14,
    },
  }),
)(TableRow);


const useStyles = makeStyles({
  table: {
    minWidth: 700,
  },
});

type HealthList = {
  list: Array<Health>
}

type Health = {
  name: string,
  status: string
}

export default function HealthTables(healthList: HealthList) {
  const classes = useStyles();

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Service Name</StyledTableCell>
            <StyledTableCell align="right">Status</StyledTableCell>
            {/* <StyledTableCell align="right">Response Time</StyledTableCell> */}
          </TableRow>
        </TableHead>
        <TableBody>
          {console.log(healthList.list)}
          {healthList.list.map((row, key) => (
            <StyledTableRow key={key}>
              {console.log(row)}
              <StyledTableCell >{row.name}</StyledTableCell>
              <StyledTableCell align="right">{
              row.status==="UNKNOWN" && <HelpOutlineOutlinedIcon></HelpOutlineOutlinedIcon> ||
              row.status==="UP" && <CheckIcon></CheckIcon> ||
              row.status==="DOWN" && <ErrorOutlineOutlinedIcon></ErrorOutlineOutlinedIcon>
              }</StyledTableCell>
              {/* <StyledTableCell align="right">10ms</StyledTableCell> */}
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
