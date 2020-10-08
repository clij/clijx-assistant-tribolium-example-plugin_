package net.haesleinhuepf.clijx.plugins;


import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij.macro.CLIJMacroPlugin;
import net.haesleinhuepf.clij.macro.CLIJOpenCLProcessor;
import net.haesleinhuepf.clij.macro.documentation.OffersDocumentation;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clijx.CLIJx;
import net.haesleinhuepf.clijx.utilities.AbstractCLIJxPlugin;
import org.scijava.plugin.Plugin;
/**
 *
 * Author: Robert Haase
 *         Thu Oct 08 18:56:13 CEST 2020
 */

@Plugin(type = CLIJMacroPlugin.class, name = "CLIJx_tribolium_Surface_Removal")
public class Tribolium_Surface_Removal extends AbstractCLIJxPlugin implements CLIJMacroPlugin, CLIJOpenCLProcessor, OffersDocumentation {

    @Override
    public String getParameterHelpText() {
        return "Image image1, ByRef Image image14, Number radiusX_3, Number radiusY_4, Number radiusZ_5, Number radiusX_8, Number radiusY_9, Number radiusZ_10, Number constant_13, Number radius_21, Number relative_center_x_24, Number relative_center_y_25, Number relative_center_z_26, Number constant_33, Number radiusX_38, Number radiusY_39, Number radiusZ_40";
    }

    @Override
    public Object[] getDefaultValues() {
        return new Object[]{null, null, new Float(10.0), new Float(10.0), new Float(0.0), new Float(2.0), new Float(2.0), new Float(2.0), new Float(70.0), new Float(5.0), new Float(0.5), new Float(0.5), new Float(0.0), new Float(0.0), new Float(4.0), new Float(4.0), new Float(0.0)};
    }

    @Override
    public boolean executeCL() {
        return tribolium_Surface_Removal(getCLIJx(), (ClearCLBuffer) args[0], (ClearCLBuffer) args[1], asFloat(args[2]), asFloat(args[3]), asFloat(args[4]), asFloat(args[5]), asFloat(args[6]), asFloat(args[7]), asFloat(args[8]), asFloat(args[9]), asFloat(args[10]), asFloat(args[11]), asFloat(args[12]), asFloat(args[13]), asFloat(args[14]), asFloat(args[15]), asFloat(args[16]));
    }

    public static boolean tribolium_Surface_Removal(CLIJx clijx, ClearCLBuffer image1, ClearCLBuffer image14, Float radiusX_3, Float radiusY_4, Float radiusZ_5, Float radiusX_8, Float radiusY_9, Float radiusZ_10, Float constant_13, Float radius_21, Float relative_center_x_24, Float relative_center_y_25, Float relative_center_z_26, Float constant_33, Float radiusX_38, Float radiusY_39, Float radiusZ_40) {

        // 
        
        // CLIJ2_topHatBox
        ClearCLBuffer image2 = clijx.create(new long[]{image1.getWidth(), image1.getHeight(), image1.getDepth()}, image1.getNativeType());
        clijx.topHatBox(image1, image2, radiusX_3, radiusY_4, radiusZ_5);
        
        
        
        // CLIJ2_detectMaxima3DBox
        ClearCLBuffer image3 = clijx.create(new long[]{image2.getWidth(), image2.getHeight(), image2.getDepth()}, image2.getNativeType());
        clijx.detectMaxima3DBox(image2, image3, radiusX_8, radiusY_9, radiusZ_10);
        
        
        
        // CLIJ2_greaterOrEqualConstant
        ClearCLBuffer image4 = clijx.create(new long[]{image2.getWidth(), image2.getHeight(), image2.getDepth()}, image2.getNativeType());
        clijx.greaterOrEqualConstant(image2, image4, constant_13);
        image2.close();
        
        
        
        // CLIJ2_binaryAnd
        ClearCLBuffer image5 = clijx.create(new long[]{image3.getWidth(), image3.getHeight(), image3.getDepth()}, image3.getNativeType());
        clijx.binaryAnd(image3, image4, image5);
        image3.close();
        image4.close();
        
        
        
        // CLIJ2_connectedComponentsLabelingBox
        ClearCLBuffer image6 = clijx.create(new long[]{image5.getWidth(), image5.getHeight(), image5.getDepth()}, clijx.Float);
        clijx.connectedComponentsLabelingBox(image5, image6);
        image5.close();
        
        
        
        // CLIJx_extendLabelsWithMaximumRadius
        ClearCLBuffer image7 = clijx.create(new long[]{image6.getWidth(), image6.getHeight(), image6.getDepth()}, image6.getNativeType());
        clijx.extendLabelsWithMaximumRadius(image6, image7, radius_21);
        image6.close();
        
        
        
        // CLIJx_labelSurface
        ClearCLBuffer image8 = clijx.create(new long[]{image7.getWidth(), image7.getHeight(), image7.getDepth()}, image7.getNativeType());
        clijx.labelSurface(image7, image8, relative_center_x_24, relative_center_y_25, relative_center_z_26);
        
        
        
        // CLIJ2_maximumZProjection
        ClearCLBuffer image9 = clijx.create(new long[]{image8.getWidth(), image8.getHeight()}, image8.getNativeType());
        clijx.maximumZProjection(image8, image9);
        image9.close();
        
        
        
        // CLIJ2_maximumZProjection
        ClearCLBuffer image10 = clijx.create(new long[]{image7.getWidth(), image7.getHeight()}, image7.getNativeType());
        clijx.maximumZProjection(image7, image10);
        image7.close();
        image10.close();
        
        
        
        // CLIJ2_equalConstant
        ClearCLBuffer image11 = clijx.create(new long[]{image8.getWidth(), image8.getHeight(), image8.getDepth()}, image8.getNativeType());
        clijx.equalConstant(image8, image11, constant_33);
        image8.close();
        
        
        
        // CLIJ2_maximumZProjection
        ClearCLBuffer image12 = clijx.create(new long[]{image1.getWidth(), image1.getHeight()}, image1.getNativeType());
        clijx.maximumZProjection(image1, image12);
        image12.close();
        
        
        
        // CLIJ2_minimum3DBox
        ClearCLBuffer image13 = clijx.create(new long[]{image11.getWidth(), image11.getHeight(), image11.getDepth()}, image11.getNativeType());
        clijx.minimum3DBox(image11, image13, radiusX_38, radiusY_39, radiusZ_40);
        image11.close();
        
        
        
        // CLIJ2_multiplyImages
        clijx.multiplyImages(image1, image13, image14);
        image13.close();
        
        
        

        return true;
    }

    @Override
    public String getDescription() {
        return "Removes cells on the surface of the Tribolium embryo";
    }

    @Override
    public String getAvailableForDimensions() {
        return "2D, 3D";
    }
}

