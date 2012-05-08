package uk.ac.cs.man.openphacts.queryexpander.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.algebra.Add;
import org.openrdf.query.algebra.And;
import org.openrdf.query.algebra.ArbitraryLengthPath;
import org.openrdf.query.algebra.Avg;
import org.openrdf.query.algebra.BNodeGenerator;
import org.openrdf.query.algebra.BindingSetAssignment;
import org.openrdf.query.algebra.Bound;
import org.openrdf.query.algebra.Clear;
import org.openrdf.query.algebra.Coalesce;
import org.openrdf.query.algebra.Compare;
import org.openrdf.query.algebra.CompareAll;
import org.openrdf.query.algebra.CompareAny;
import org.openrdf.query.algebra.Copy;
import org.openrdf.query.algebra.Count;
import org.openrdf.query.algebra.Create;
import org.openrdf.query.algebra.Datatype;
import org.openrdf.query.algebra.DeleteData;
import org.openrdf.query.algebra.Difference;
import org.openrdf.query.algebra.Distinct;
import org.openrdf.query.algebra.EmptySet;
import org.openrdf.query.algebra.Exists;
import org.openrdf.query.algebra.Extension;
import org.openrdf.query.algebra.ExtensionElem;
import org.openrdf.query.algebra.Filter;
import org.openrdf.query.algebra.FunctionCall;
import org.openrdf.query.algebra.Group;
import org.openrdf.query.algebra.GroupConcat;
import org.openrdf.query.algebra.GroupElem;
import org.openrdf.query.algebra.IRIFunction;
import org.openrdf.query.algebra.If;
import org.openrdf.query.algebra.In;
import org.openrdf.query.algebra.InsertData;
import org.openrdf.query.algebra.Intersection;
import org.openrdf.query.algebra.IsBNode;
import org.openrdf.query.algebra.IsLiteral;
import org.openrdf.query.algebra.IsNumeric;
import org.openrdf.query.algebra.IsResource;
import org.openrdf.query.algebra.IsURI;
import org.openrdf.query.algebra.Join;
import org.openrdf.query.algebra.Label;
import org.openrdf.query.algebra.Lang;
import org.openrdf.query.algebra.LangMatches;
import org.openrdf.query.algebra.LeftJoin;
import org.openrdf.query.algebra.Like;
import org.openrdf.query.algebra.Load;
import org.openrdf.query.algebra.LocalName;
import org.openrdf.query.algebra.MathExpr;
import org.openrdf.query.algebra.Max;
import org.openrdf.query.algebra.Min;
import org.openrdf.query.algebra.Modify;
import org.openrdf.query.algebra.Move;
import org.openrdf.query.algebra.MultiProjection;
import org.openrdf.query.algebra.Namespace;
import org.openrdf.query.algebra.Not;
import org.openrdf.query.algebra.Or;
import org.openrdf.query.algebra.Order;
import org.openrdf.query.algebra.OrderElem;
import org.openrdf.query.algebra.Projection;
import org.openrdf.query.algebra.ProjectionElem;
import org.openrdf.query.algebra.ProjectionElemList;
import org.openrdf.query.algebra.QueryModelNode;
import org.openrdf.query.algebra.QueryModelVisitor;
import org.openrdf.query.algebra.QueryRoot;
import org.openrdf.query.algebra.Reduced;
import org.openrdf.query.algebra.Regex;
import org.openrdf.query.algebra.SameTerm;
import org.openrdf.query.algebra.Sample;
import org.openrdf.query.algebra.Service;
import org.openrdf.query.algebra.SingletonSet;
import org.openrdf.query.algebra.Slice;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.Str;
import org.openrdf.query.algebra.Sum;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Union;
import org.openrdf.query.algebra.ValueConstant;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.ZeroLengthPath;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;
import uk.ac.cs.man.openphacts.queryexpander.QueryExpansionException;

public class ExtensionMapperVisitor implements QueryModelVisitor<QueryExpansionException>{

    private HashMap<String,String> extensionMappings = new HashMap<String,String>();
    
    private StringBuilder extension;
    
    private ExtensionMapperVisitor(){
    }
        
    public static HashMap<String,String> getMappings(TupleExpr tupleExpr) throws QueryExpansionException{
        ExtensionMapperVisitor listener = new ExtensionMapperVisitor();
        tupleExpr.visit(listener);
        return listener.extensionMappings;
    }

    @Override
    public void meet(QueryRoot qr) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Add add) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(And and) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(ArbitraryLengthPath alp) throws QueryExpansionException {
        //These are solved without using Extension Mapping so stop looking
    }

    @Override
    public void meet(Avg avg) throws QueryExpansionException {
        this.extension.append(" AVG (");
        avg.getArg().visit(this);
        this.extension.append(") ");        
    }

    @Override
    public void meet(BindingSetAssignment bsa) throws QueryExpansionException {
        //No TuplExpr children
        //No Extensions in Binding sets so stop looking
    }

    @Override
    public void meet(BNodeGenerator bng) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Bound bound) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Clear clear) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Coalesce clsc) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Compare cmpr) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(CompareAll ca) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(CompareAny ca) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Copy copy) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Count count) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Create create) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Datatype dtp) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(DeleteData dd) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Difference dfrnc) throws QueryExpansionException {
        //should be ok bit keep checking just in case.
        dfrnc.getLeftArg().visit(this);
        dfrnc.getRightArg().visit(this);
    }

    @Override
    public void meet(Distinct dstnct) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(EmptySet es) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Exists exists) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Extension extnsn) throws QueryExpansionException {
        for (ExtensionElem extensionElem:extnsn.getElements()){
            meet(extensionElem);
        }
        extnsn.getArg().visit(this);
    }

    @Override
    public void meet(ExtensionElem ee) throws QueryExpansionException {
        String name = ee.getName();
        if (name.startsWith("-") || name.startsWith("_")){
            extension = new StringBuilder();
            ee.getExpr().visit(this);
            extensionMappings.put(ee.getName(), extension.toString());
            extension = null;
        }
    }

    @Override
    public void meet(Filter filter) throws QueryExpansionException {
        filter.getArg().visit(this);
    }

    @Override
    public void meet(FunctionCall fc) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Group group) throws QueryExpansionException {
        //Should be ok but keep checking just in case
        group.getArg().visit(this);
    }

    @Override
    public void meet(GroupConcat gc) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(GroupElem ge) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(If i) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(In in) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(InsertData id) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Intersection i) throws QueryExpansionException {
        i.getLeftArg().visit(this);
        i.getRightArg().visit(this);
    }

    @Override
    public void meet(IRIFunction irif) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(IsBNode ibn) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(IsLiteral il) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(IsNumeric in) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(IsResource ir) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(IsURI isuri) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Join join) throws QueryExpansionException {
        //should be ok bit keep checking just in case.
        join.getLeftArg().visit(this);
        join.getRightArg().visit(this);
    }

    @Override
    public void meet(Label label) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Lang lang) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(LangMatches lm) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(LeftJoin lj) throws QueryExpansionException {
        //should be ok bit keep checking just in case.
        lj.getLeftArg().visit(this);
        lj.getRightArg().visit(this);
    }

    @Override
    public void meet(Like like) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Load load) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(LocalName ln) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(MathExpr me) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Max max) throws QueryExpansionException {
        this.extension.append(" MAX(");
        max.getArg().visit(this);
        this.extension.append(") ");        
    }

    @Override
    public void meet(Min min) throws QueryExpansionException {
        this.extension.append(" MIN(");
        min.getArg().visit(this);
        this.extension.append(") ");        
    }

    @Override
    public void meet(Modify modify) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Move move) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(MultiProjection mp) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Namespace nmspc) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Not not) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Or or) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Order order) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(OrderElem oe) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Projection prjctn) throws QueryExpansionException {
        //in subquery so stop
    }

    @Override
    public void meet(ProjectionElem pe) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(ProjectionElemList pel) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Reduced rdcd) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Regex regex) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(SameTerm st) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Sample sample) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Service srvc) throws QueryExpansionException {
       //Don't expect exstension here but not sure so lets check
        srvc.getArg().visit(this);
    }

    @Override
    public void meet(SingletonSet ss) throws QueryExpansionException {
       //Nothing here
    }

    @Override
    public void meet(Slice slice) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(StatementPattern sp) throws QueryExpansionException {
        //Extensions come before SatemementPatterns so stop looking
    }

    @Override
    public void meet(Str str) throws QueryExpansionException {
        this.extension.append(" STR(");
        str.getArg().visit(this);
        this.extension.append(") ");        
    }

    @Override
    public void meet(Sum sum) throws QueryExpansionException {
        this.extension.append("SUM (");
        sum.getArg().visit(this);
        this.extension.append(") ");        
    }

    @Override
    public void meet(Union union) throws QueryExpansionException {
        union.getLeftArg().visit(this);
        union.getRightArg().visit(this);
    }

    @Override
    public void meet(ValueConstant vc) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Var var) throws QueryExpansionException {
        if (var.hasValue()){
            Value value = var.getValue();
            if (value instanceof URI){
                this.extension.append("<");
                this.extension.append(value.stringValue());
                this.extension.append(">"); 
            } else {
                this.extension.append(value);
            }
        } else if (var.isAnonymous()){
            throw new UnsupportedOperationException("Anon Not supported yet.");
        } else {
            this.extension.append(" ?");
            this.extension.append(var.getName());
        }
        this.extension.append(" ");
    }

    @Override
    public void meet(ZeroLengthPath zlp) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meetOther(QueryModelNode qmn) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
